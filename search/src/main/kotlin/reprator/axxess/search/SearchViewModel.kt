package reprator.axxess.search

import androidx.annotation.VisibleForTesting
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import reprator.axxess.base.util.AppCoroutineDispatchers
import reprator.axxess.base.util.useCases.ErrorResult
import reprator.axxess.base.util.useCases.Success
import reprator.axxess.base_android.SearchModal
import reprator.axxess.base_android.util.event.Event
import reprator.axxess.search.domain.usecase.SearchUseCase

private const val DEBOUNCE_TIME = 250L

class SearchViewModel @ViewModelInject constructor(
    private val searchUseCase: SearchUseCase,
    private val coroutineDispatchers: AppCoroutineDispatchers,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val searchQuery = MutableStateFlow("")

    private val _searchItemList: MutableLiveData<List<SearchModal>> = MutableLiveData()
    val searchItemList: LiveData<List<SearchModal>>
        get() = _searchItemList

    private val _showLoader: MutableLiveData<Boolean> = MutableLiveData()
    val showLoader: LiveData<Boolean>
        get() = _showLoader

    private val _isError: MutableLiveData<Event<String>> = MutableLiveData()
    val isError: LiveData<Event<String>>
        get() = _isError

    private val _itemSelected: MutableLiveData<Event<SearchModal>> = MutableLiveData()
    val itemSelected: LiveData<Event<SearchModal>>
        get() = _itemSelected

    fun setSearchQuery(query: String) {
        searchQuery.value = query
        savedStateHandle.set(KEY_SEARCH, query)
    }

    fun positionClicked(positionClicked: Int) {
        _itemSelected.value = Event(searchItemList.value!![positionClicked])
    }

    init {
        setListenerForMutableState()
        checkForKilledState()
    }

    @VisibleForTesting
    private fun setListenerForMutableState() {
        viewModelScope.launch {
            searchQuery.debounce(DEBOUNCE_TIME)
                .collectLatest { query ->

                    if (query.isBlank())
                        return@collectLatest

                    val job = searchServer(query)
                    job.invokeOnCompletion {
                        _showLoader.value = false
                    }
                    job.join()
                }
        }
    }

    private fun checkForKilledState() {
        savedStateHandle.get<String>(KEY_SEARCH)?.let {
            setSearchQuery(it)
        }
    }

    @VisibleForTesting
    suspend fun searchServer(query: String) =
        viewModelScope.launch {
            _showLoader.value = true
            searchUseCase(query)
                .flowOn(coroutineDispatchers.io)
                .catch {
                    _isError.value = Event(it.message!!)
                }
                .collectLatest {
                    when (it) {
                        is Success ->
                            _searchItemList.value = it.data
                        is ErrorResult -> {
                            _isError.value = Event(it.message!!)
                        }
                    }
                }
        }

    companion object {
        private const val KEY_SEARCH = "itemSearching"
    }
}
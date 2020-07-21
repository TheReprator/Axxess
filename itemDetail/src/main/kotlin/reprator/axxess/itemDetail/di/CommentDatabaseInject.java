package reprator.axxess.itemDetail.di;

import android.content.Context;

import androidx.room.Room;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.android.scopes.ActivityScoped;
import reprator.axxess.base_android.AppNavigator;
import reprator.axxess.base_android.BackNavigator;
import reprator.axxess.itemDetail.AppExecutor;
import reprator.axxess.itemDetail.data.CommentDao;
import reprator.axxess.itemDetail.data.CommentDataBase;
import reprator.axxess.itemDetail.repository.CommentDataSource;
import reprator.axxess.itemDetail.repository.CommentMapperImpl;
import reprator.axxess.itemDetail.repository.CommentRepository;

@InstallIn(ActivityComponent.class)
@Module
public class CommentDatabaseInject {

    @ActivityScoped
    @Provides
    CommentDataBase provideDatabase(
            @ApplicationContext Context context,
            String dataBaseName
    ) {

        return Room.databaseBuilder(
                context,
                CommentDataBase.class,
                dataBaseName
        ).fallbackToDestructiveMigration().build();
    }

    @ActivityScoped
    @Provides
    String provideDatabaseName() {
        return "commentDb.db";
    }

    @ActivityScoped
    @Provides
    CommentDao provideCommentDao(CommentDataBase db) {
        return db.commentDao();
    }

    @ActivityScoped
    @Provides
    CommentRepository provideCommentRepository(CommentDao commentDao,
                                               AppExecutor appExecutor, CommentMapperImpl commentMapper) {
        return new CommentDataSource(commentDao, commentMapper, appExecutor);
    }

    @Provides
    BackNavigator provideBackNavigator(AppNavigator appNavigator) {
        return appNavigator;
    }
}

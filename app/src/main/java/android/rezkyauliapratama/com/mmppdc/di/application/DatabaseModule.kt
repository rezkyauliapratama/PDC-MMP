package android.rezkyauliapratama.com.mmppdc.di.application

import dagger.Module
import dagger.Provides

/**
 * Created by Rezky Aulia Pratama on 25/8/18.
 */
@Module
class DatabaseModule{


    @Provides
    @DatabaseInfo
    fun provideDatabaseName():String{
        return "football-db.db"
    }

    @Provides
    @DatabaseInfo
    fun provideDatabaseVersion():Int{
        return 1
    }
/*
    @Singleton
    @Provides
    internal fun provideDatabaseHelper(@ApplicationContext context: Context, @DatabaseInfo name:String, @DatabaseInfo version: Int): MyDatabaseOpenHelper {
        return MyDatabaseOpenHelper(context,name,version)
    }*/
}
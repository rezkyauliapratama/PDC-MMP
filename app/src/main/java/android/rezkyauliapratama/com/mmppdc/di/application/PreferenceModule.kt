package android.rezkyauliapratama.com.mmppdc.di.application

import android.content.Context
import android.preference.PreferenceManager
import android.rezkyauliapratama.com.mmppdc.data.preference.PrefManager
import com.securepreferences.SecurePreferences
import dagger.Module
import dagger.Provides

@Module
class PreferenceModule {

    @Provides
    fun provideSecureManager(@ApplicationContext context: Context): SecurePreferences {
        return SecurePreferences(context)
    }

    @Provides
    fun providePreferenceManager(securePreferences: SecurePreferences): PrefManager {
        return PrefManager(securePreferences)
    }

}
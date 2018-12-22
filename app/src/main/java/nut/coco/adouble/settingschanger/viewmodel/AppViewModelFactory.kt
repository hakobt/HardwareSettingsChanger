package nut.coco.adouble.settingschanger.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import nut.coco.adouble.settingschanger.App

/**
 * Created by Hakob Tovmasyan on 12/22/18
 * Package nut.coco.adouble.settingschanger.viewmodel
 *
 *
 * ViewModel factory class to create viewmodels with custom constructor parameters.
 */

@Suppress("UNCHECKED_CAST")
class AppViewModelFactory: ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(App.instance.settingsRepository) as T
        }

        throw IllegalArgumentException("$modelClass is not configured for this factory")
    }
}
package com.argahutama.submission.made.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.lifecycleScope
import com.argahutama.submission.core.base.BaseActivity
import com.argahutama.submission.custom_ui.CustomSnack
import com.argahutama.submission.made.R
import com.argahutama.submission.made.databinding.ActivityMainBinding
import com.argahutama.submission.made.movie.MovieFragment
import com.argahutama.submission.made.tvshow.TvShowFragment
import kotlinx.coroutines.*
import org.koin.android.viewmodel.ext.android.viewModel

@FlowPreview
@ExperimentalCoroutinesApi
class MainActivity : BaseActivity() {
    private var backPressed = false
    private var backPressJob: Job? = null
    private var visibleMenuId: Int? = null
    override val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override val viewModel by viewModel<MainViewModel>()

    override fun setup() {
        super.setup()
        initInitialMenu()
    }

    override fun initView() = with(binding) {
        bnvMainMenu.run {
            selectedItemId = R.id.main_menu_movies
            selectMenu(R.id.main_menu_movies)
            itemIconTintList = null
        }
    }

    override fun initAction() = with(binding) {
        bnvMainMenu.setOnItemSelectedListener(null)
        bnvMainMenu.setOnItemSelectedListener { selectMenu(it.itemId) }
    }

    override fun onBackPressed() {
        if (!backPressed) {
            backPressed = true
            backPressJob?.cancel()
            backPressJob = lifecycleScope.launch {
                delay(2000)
                backPressed = false
            }
            showSnackbar(getString(R.string.press_back_to_exit), CustomSnack.WARNING)
        } else {
            super.onBackPressed()
        }
    }

    private fun changeNavigation(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameContainer, fragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()
    }

    private fun initInitialMenu() = with(binding) {
        val menuId = visibleMenuId
        bnvMainMenu.selectedItemId = menuId ?: 0
    }

    private val className: String get() = "com.argahutama.submission.favorite.FavoriteFragment"

    private fun moveToFavoriteFragment() {
        val fragment = instantiateFragment(className)
        if (fragment != null) changeNavigation(fragment)
    }

    private fun instantiateFragment(className: String): Fragment? = try {
        Class.forName(className).newInstance() as Fragment
    } catch (e: Exception) {
        showSnackbar(e.cause?.message.orEmpty(), CustomSnack.FAILED)
        null
    }

    private fun selectMenu(menuId: Int): Boolean {
        if (visibleMenuId == menuId) return false

        visibleMenuId = menuId
        return when (menuId) {
            R.id.main_menu_movies -> {
                changeNavigation(MovieFragment())
                true
            }
            R.id.main_menu_tv_shows -> {
                changeNavigation(TvShowFragment())
                true
            }
            R.id.main_menu_favorites -> {
                moveToFavoriteFragment()
                true
            }
            else -> false
        }
    }
}
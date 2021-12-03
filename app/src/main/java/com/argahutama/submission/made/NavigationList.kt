package com.argahutama.submission.made

import com.argahutama.submission.core.navigation.NavigationDirection
import com.argahutama.submission.made.detail.DetailActivity
import com.argahutama.submission.made.main.MainActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@ExperimentalCoroutinesApi
@FlowPreview
val navigationMapper = mapOf(
    NavigationDirection.Main::class.java to MainActivity::class.java,
    NavigationDirection.Detail::class.java to DetailActivity::class.java
)
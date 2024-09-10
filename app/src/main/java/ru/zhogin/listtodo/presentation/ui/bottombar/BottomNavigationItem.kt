package ru.zhogin.listtodo.presentation.ui.bottombar

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem(
    val title: String,
    val icon: ImageVector,
    val route: String,
    val badgeCount: Int? = null,
)

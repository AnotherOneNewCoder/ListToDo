package ru.zhogin.listtodo.presentation.ui.bottombar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.zhogin.app.uikit.TabText
import ru.zhogin.app_settings.presentation.state.ColorsState
import ru.zhogin.listtodo.R

@Composable
internal fun BottomBar(
    navHostController: NavHostController,
    counterNotDone: Int,
    counterDone: Int,
    colorState: ColorsState,
) {
    NavigationBar(
        modifier = Modifier.background(
            colorState.backgroundColor).clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)).border(0.5.dp,colorState.borderColor,RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp) ),
        containerColor = colorState.backgroundCardColor
    ) {
        val items = listOf(
            BottomNavigationItem(
                title = stringResource(id = R.string.in_progress),
                icon = ImageVector.vectorResource(id = R.drawable.ic_task_in_progress),
                route = "tasks_in_progress",
                badgeCount = counterNotDone,
                ),
            BottomNavigationItem(
                title = stringResource(id = R.string.tasks_done_text),
                icon = ImageVector.vectorResource(id = R.drawable.ic_task_done),
                route = "tasks_done",
                badgeCount = counterDone,
                ),
            BottomNavigationItem(
                title = stringResource(id = R.string.settings_text),
                icon = ImageVector.vectorResource(id = R.drawable.ic_settings),
                route = "settings_screen",
                ),
        )
        val navBackStackEntry by navHostController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            NavigationBarItem(

                selected = currentRoute == item.route,
                onClick = { navHostController.navigate(item.route) },
                icon = { BadgedBox(
                    badge = {
                        if(item.badgeCount != null && item.badgeCount != 0) {
                            Badge(
                                containerColor = colorState.badgeColor,
                                contentColor = colorState.textColor
                            ) {

                                Text(text = item.badgeCount.toString())
                            }
                        }
                    }) {
                    Icon(imageVector = item.icon, contentDescription = item.title)
                } },
                label = {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.TabText,
                        color = if (currentRoute == item.route) colorState.borderColor else colorState.textColor


                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = colorState.borderColor,
                    unselectedIconColor = colorState.textColor,
                    selectedTextColor = colorState.borderColor,
                    unselectedTextColor = colorState.textColor,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}
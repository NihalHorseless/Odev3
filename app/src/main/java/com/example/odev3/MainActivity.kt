package com.example.odev3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.odev3.ui.theme.DarkAccent
import com.example.odev3.ui.theme.DarkPrimary
import com.example.odev3.ui.theme.Odev3Theme
import com.example.odev3.ui.theme.accent
import com.example.odev3.ui.theme.exo_italic
import com.example.odev3.ui.theme.exo_regular
import com.example.odev3.ui.theme.gray
import com.example.odev3.ui.theme.primary

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Odev3Theme {
                MainScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(darkTheme:Boolean = isSystemInDarkTheme()) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp
    val screenWidth = configuration.screenWidthDp
    val newsItemModifier = Modifier
        .fillMaxWidth()
        .height((screenHeight / 6).dp)
        .width((screenWidth / 6).dp)
        .padding(8.dp)
    Scaffold(topBar = {

        CenterAlignedTopAppBar(
            title = {
                Image(
                    painter = painterResource(if(darkTheme) R.drawable.logo_third else R.drawable.logo_second),
                    contentDescription = stringResource(R.string.logo_content_description)
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = if(darkTheme) DarkPrimary else primary,
                titleContentColor = if(darkTheme) DarkAccent else accent
            ), actions = {
                IconButton(
                    modifier = Modifier,
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = if(darkTheme) primary else gray,
                        containerColor = if(darkTheme) DarkPrimary else primary
                    ),
                    onClick = { }) {
                    Image(
                        painter = painterResource(if(darkTheme) R.drawable.baseline_account_circle_dark else R.drawable.baseline_account_circle_24),
                        contentDescription = stringResource(R.string.account_button_content_description)
                    )
                }

            }


        )
    }, bottomBar = {
        BottomAppBar(containerColor = if(darkTheme) gray else Color.White, contentColor = if(darkTheme) DarkPrimary else primary) {
            Row(
                modifier = Modifier.fillMaxSize().weight(0.3f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                BottomNavItem(R.drawable.baseline_newspaper_24,stringResource(R.string.nav_news),Modifier.weight(0.3f))
                BottomNavItem(R.drawable.race_flag,stringResource(R.string.nav_racing),Modifier.weight(0.3f))
                BottomNavItem(R.drawable.racing_helmet,stringResource(R.string.nav_drivers),Modifier.weight(0.3f))
            }
        }
    }
    )
    { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NewsItem(
                modifier = newsItemModifier,
                stringResource(R.string.news_item_1),
                R.drawable.news_first, newsType = stringResource(R.string.news_type_news)
            )
            NewsItem(
                modifier = newsItemModifier,
                stringResource(R.string.news_item_2),
                R.drawable.news_second, newsType = stringResource(R.string.news_type_news)
            )
            NewsItem(
                modifier = newsItemModifier,
                stringResource(R.string.news_item_3),
                R.drawable.news_third, newsType = stringResource(R.string.news_type_news)
            )
            NewsItem(
                modifier = newsItemModifier,
                stringResource(R.string.news_item_4),
                R.drawable.news_fourth, newsType = stringResource(R.string.news_type_poll)
            )
            NewsItem(
                modifier = newsItemModifier,
                stringResource(R.string.news_item_5),
                R.drawable.news_fifth, newsType = stringResource(R.string.news_type_opinion)
            )
        }

    }
}
@Composable
fun BottomNavItem(iconRes:Int,itemContent:String,modifier: Modifier) {
    IconButton(onClick = { }, modifier = modifier.fillMaxHeight()) {
        Column(modifier = Modifier.fillMaxSize())
        {
            Icon(
                painter = painterResource(id = iconRes),
                modifier = Modifier
                    .size(32.dp)
                    .align(Alignment.CenterHorizontally),
                contentDescription = null
            )
            Text(
                text = itemContent,
                textAlign = TextAlign.Center,
                fontFamily = exo_regular,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun NewsItem(modifier: Modifier, newsContent: String, newsImageRes: Int, newsType: String) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.4f)
                .clip(shape = RoundedCornerShape(8.dp)),
            painter = painterResource(newsImageRes),
            contentDescription = "News Image",
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(start = 8.dp),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = newsType,
                textAlign = TextAlign.Start,
                color = primary,
                fontFamily = exo_italic,
                modifier = Modifier.weight(30f)
            )
            Text(
                text = newsContent,
                textAlign = TextAlign.Start,
                color = accent,
                fontFamily = exo_regular,
                modifier = Modifier.weight(70f)
            )
        }
    }
}


@Preview(showBackground = true, locale = "tr")
@Composable
fun Preview() {
    Odev3Theme {
        MainScreen()
    }
}
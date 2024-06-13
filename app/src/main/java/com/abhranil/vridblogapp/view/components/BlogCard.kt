package com.abhranil.vridblogapp.view.components

import android.text.Html
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.abhranil.vridblogapp.data.model.main.VridBlogDataItem
import com.abhranil.vridblogapp.view.navigation.VridBlogScreens

@Composable
fun BlogCard(blogDataItem: VridBlogDataItem, navController: NavController) {
    OutlinedCard(
        modifier = Modifier
            .clickable {
                Log.d("BlogCard", "BlogCard: ${blogDataItem.id}")
                navController.navigate(VridBlogScreens.DetailsScreen.route + "/${blogDataItem.id}")
            }
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        val imgUrl = if(!blogDataItem.jetpack_featured_media_url.isNullOrEmpty())
                blogDataItem.jetpack_featured_media_url
            else "https://static.vecteezy.com/system/resources/previews/005/337/799/original/icon-image-not-found-free-vector.jpg"
        Surface(modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 12.dp, bottom = 8.dp),
            shape = RoundedCornerShape(20.dp)) {
            ShimmerImage(imgUrl = imgUrl, modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight())
        }
        Column(modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 16.dp)) {
            val titleUnescape = Html.fromHtml(blogDataItem.title.rendered, Html.FROM_HTML_MODE_LEGACY).toString()
            Text(text = titleUnescape, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Date: ${blogDataItem.date.substring(0,10)}")
        }
    }
}
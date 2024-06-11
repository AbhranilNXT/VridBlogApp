package com.abhranil.vridblogapp.data.model.main

data class VridBlogDataItem(
    val _links: com.abhranil.vridblogapp.data.model.main.Links,
    val aioseo_notices: List<Any>,
    val author: Int,
    val categories: List<Int>,
    val class_list: List<String>,
    val comment_status: String,
    val content: com.abhranil.vridblogapp.data.model.main.Content,
    val date: String,
    val date_gmt: String,
    val excerpt: com.abhranil.vridblogapp.data.model.main.Excerpt,
    val featured_media: Int,
    val format: String,
    val guid: com.abhranil.vridblogapp.data.model.main.Guid,
    val id: Int,
    val jetpack_related_posts: List<Any>,
    val jetpack_featured_media_url: String,
    val jetpack_likes_enabled: Boolean,
    val jetpack_publicize_connections: List<Any>,
    val jetpack_sharing_enabled: Boolean,
    val jetpack_shortlink: String,
    val link: String,
    val meta: com.abhranil.vridblogapp.data.model.main.Meta,
    val modified: String,
    val modified_gmt: String,
    val ping_status: String,
    val slug: String,
    val status: String,
    val sticky: Boolean,
    val tags: List<Int>,
    val template: String,
    val title: com.abhranil.vridblogapp.data.model.main.Title,
    val type: String
)
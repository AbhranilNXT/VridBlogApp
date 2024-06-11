package com.abhranil.vridblogapp.data.model.main

data class Links(
    val about: List<com.abhranil.vridblogapp.data.model.main.About>,
    val author: List<com.abhranil.vridblogapp.data.model.main.Author>,
    val collection: List<com.abhranil.vridblogapp.data.model.main.Collection>,
    val curies: List<com.abhranil.vridblogapp.data.model.main.Cury>,
    val predecessor_version: List<com.abhranil.vridblogapp.data.model.main.PredecessorVersion>,
    val replies: List<com.abhranil.vridblogapp.data.model.main.Reply>,
    val self: List<com.abhranil.vridblogapp.data.model.main.Self>,
    val version_history: List<com.abhranil.vridblogapp.data.model.main.VersionHistory>,
    val wp_attachment: List<com.abhranil.vridblogapp.data.model.main.WpAttachment>,
    val wp_featuredmedia: List<com.abhranil.vridblogapp.data.model.main.WpFeaturedmedia>,
    val wp_term: List<com.abhranil.vridblogapp.data.model.main.WpTerm>
)
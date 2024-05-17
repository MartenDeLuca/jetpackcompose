package com.example.jetpackcomposecatalago.ui.theme

data class CheckInfo(var title:String, var selected:Boolean = false, var onCheckedChange:(Boolean) -> Unit)
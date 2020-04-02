package kz.kotlin.x10app.commons.views

import kz.kotlin.x10app.modules.base.VoidCompletion

data class DialogButtonContext(
    var title: String,
    var callback: VoidCompletion?
)
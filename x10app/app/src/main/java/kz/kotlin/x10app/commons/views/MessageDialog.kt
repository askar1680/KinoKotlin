package kz.kotlin.x10app.commons.views

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import kz.kotlin.x10app.modules.base.VoidCompletion
import kotlinx.android.synthetic.main.dialog_message.*
import kz.kotlin.x10app.R

class MessageDialog private constructor(
	context: Context,
	private var builder: Builder
) : Dialog(context), View.OnClickListener {


	override fun onCreate(savedInstanceState: Bundle?) {
		setContentView(R.layout.dialog_message)
		
		messageDialogTitle.text = builder.title
		messageDialogMessage.text = builder.message
		
		if (builder.positiveButtonContext != null) {
			messageDialogPositiveButton.visibility = View.VISIBLE
			messageDialogPositiveButton.text = builder.positiveButtonContext?.title
			messageDialogPositiveButton.setOnClickListener(this)
		}
		if (builder.negativeButtonContext != null) {
			messageDialogNegativeButton.visibility = View.VISIBLE
			messageDialogNegativeButton.text = builder.negativeButtonContext?.title
			messageDialogNegativeButton.setOnClickListener(this)
		}
		if (builder.positiveButtonContext != null && builder.negativeButtonContext != null) {
			messageDialogButtonSeparator.visibility = View.VISIBLE
		}
	}
	
	override fun onClick(v: View?) {
		when (v) {
			messageDialogPositiveButton -> builder.positiveButtonContext?.callback?.invoke()
			messageDialogNegativeButton -> builder.negativeButtonContext?.callback?.invoke()
		}
		dismiss()
	}
	
	class Builder(private val context: Context) {
		
		var title: String = ""
		var message: String = ""
		
		var negativeButtonContext: DialogButtonContext? = null
		var positiveButtonContext: DialogButtonContext? = null
		
		fun setTitle(title: String): Builder {
			this.title = title
			return this
		}
		
		fun setMessage(message: String): Builder {
			this.message = message
			return this
		}
		
		fun setNegativeButton(title: String, callback: VoidCompletion?): Builder {
			negativeButtonContext = DialogButtonContext(title = title, callback = callback)
			return this
		}
		
		fun setPositiveButton(title: String, callback: VoidCompletion?): Builder {
			positiveButtonContext = DialogButtonContext(title = title, callback = callback)
			return this
		}
		
		fun build(): MessageDialog {
			return MessageDialog(context = context, builder = this)
		}
		
		fun show(): MessageDialog {
			val messageDialog = build()
			messageDialog.show()
			return messageDialog
		}
	}
}
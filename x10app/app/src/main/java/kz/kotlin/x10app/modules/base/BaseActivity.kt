package kz.kotlin.x10app.modules.base

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import kz.kotlin.x10app.commons.views.MessageDialog
import kz.kotlin.x10app.modules.authorization.login.view.LoginActivity

abstract class BaseActivity : AppCompatActivity(), BaseViewInterface {
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)

        progressBar = ProgressBar(this)
        viewDidLoad()
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun openActivityOnTokenExpire() {
        startActivity(LoginActivity.getStartIntent(this))
        finish()
    }

    override fun onError(resId: Int, completion: VoidCompletion?) {
        onError(getString(resId), completion)
    }

    override fun onError(message: String, completion: VoidCompletion?) {
        showAlert(title = "Attention", message = message, completion = completion)
    }

    override fun showMessage(message: String, completion: VoidCompletion?) {
        showAlert(title = "Message", message = message, completion = completion)
    }

    private fun showAlert(title: String, message: String, completion: VoidCompletion?) {
        MessageDialog.Builder(context = this)
            .setTitle(title)
            .setMessage(message)
            .setNegativeButton("Cancel") {
                completion?.invoke()
            }
            .show()
    }

    override fun showMessage(resId: Int, completion: VoidCompletion?) {
        showMessage(getString(resId), completion)
    }

    override fun showSuccess(message: String, completion: VoidCompletion?) {

    }

    override fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}
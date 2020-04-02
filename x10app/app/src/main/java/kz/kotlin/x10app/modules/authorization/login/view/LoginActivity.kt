package kz.kotlin.x10app.modules.authorization.login.view

import android.content.Context
import android.content.Intent
import kotlinx.android.synthetic.main.activity_login.*
import kz.kotlin.x10app.R
import kz.kotlin.x10app.commons.extensions.shakeView
import kz.kotlin.x10app.modules.authorization.login.contract.LoginViewInterface
import kz.kotlin.x10app.modules.authorization.login.presenter.LoginPresenter
import kz.kotlin.x10app.modules.authorization.sms.view.SmsActivity
import kz.kotlin.x10app.modules.base.BaseActivity

class LoginActivity : BaseActivity(), LoginViewInterface {

    var token: String? = null

    override fun set(token: String) {
        this.token = token
    }

    override fun openSmsActivity() {
        val intent = SmsActivity.getStartIntent(context = this)
        phoneNumberEditText.unMaskedText?.let {
            intent.putExtra("phoneNumber", it)
        }
        token?.let {
            intent.putExtra("token", it)
        }
        startActivity(intent)
    }

    override fun openRegistrationActivity() {
        println("OPEN REGISTER")
    }

    override fun shakePhoneNumber() {
        phoneNumberEditText.shakeView()
    }

    private val presenter = LoginPresenter()

    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }

    override val layoutId: Int = R.layout.activity_login

    override fun viewDidLoad() {
        presenter.onAttach(view = this)
        setupNextButtonClick()
    }

    private fun setupNextButtonClick() {
        nextButton.setOnClickListener {
            presenter.nextButtonTapped(phoneNumberEditText.unMaskedText ?: "")
        }
    }
}
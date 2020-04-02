package kz.kotlin.x10app.modules.authorization.sms.view

import android.content.Context
import android.content.Intent
import android.util.Log
import kotlinx.android.synthetic.main.activity_login.nextButton
import kotlinx.android.synthetic.main.activity_sms.*
import kz.kotlin.x10app.R
import kz.kotlin.x10app.commons.extensions.shakeView
import kz.kotlin.x10app.modules.authorization.sms.contract.SmsPresenterInterface
import kz.kotlin.x10app.modules.authorization.sms.contract.SmsViewInterface
import kz.kotlin.x10app.modules.authorization.sms.presenter.SmsPresenter
import kz.kotlin.x10app.modules.base.BaseActivity

class SmsActivity : BaseActivity(), SmsViewInterface {

    var tokenSms: String? = null

    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, SmsActivity::class.java)
        }
    }

    override fun shakeSmsEditText() {
        smsCodeEditText.shakeView()
    }

    override fun setSmsToken(token: String) {
        this.tokenSms = token
    }

    override fun openRegistrationActivity() {

    }

    val presenter: SmsPresenterInterface = SmsPresenter()

    override val layoutId: Int = R.layout.activity_sms

    override fun viewDidLoad() {
        presenter.onAttach(this)
        setupNextButton()

        val phoneNumber = intent.getStringExtra("phoneNumber")
        phoneNumber?.let {
            phoneNumberTextView.text = it
        }

        val token = intent.getStringExtra("token")
        Log.e("TAG: TOKEN%$", token ?: "Null ept")
        presenter.set(token)
    }

    private fun setupNextButton() {
        nextButton.setOnClickListener {
            presenter.nextButtonTapped(smsCodeEditText.text.toString())
        }
    }
}

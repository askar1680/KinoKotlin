package gidirect.gratanet.gratainternational.com.gidirectclient.module.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kz.kotlin.x10app.modules.base.BaseActivity
import kz.kotlin.x10app.modules.base.BaseViewInterface
import kz.kotlin.x10app.modules.base.VoidCompletion

abstract class BaseFragment: Fragment(), BaseViewInterface {
	private var baseActivity: BaseActivity? = null
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setHasOptionsMenu(false)
	}
	
	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return inflater.inflate(layoutId, container, false)
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		viewDidLoad()
	}
	
	override fun onAttach(context: Context) {
		super.onAttach(context)
		if (context is BaseActivity) {
			baseActivity = context
		}
	}
	
	override fun openActivityOnTokenExpire() {
		baseActivity?.openActivityOnTokenExpire()
	}
	
	override fun onError(resId: Int, completion: VoidCompletion?) {
		baseActivity?.onError(resId, completion)
	}
	
	override fun onError(message: String, completion: VoidCompletion?) {
		baseActivity?.onError(message, completion)
	}
	
	override fun showSuccess(message: String, completion: VoidCompletion?) {
		baseActivity?.showSuccess(message = message, completion = completion)
	}
	
	override fun showMessage(message: String, completion: VoidCompletion?) {
		baseActivity?.showMessage(message, completion)
	}
	
	override fun showMessage(resId: Int, completion: VoidCompletion?) {
		baseActivity?.showMessage(resId, completion)
	}
	
	override fun showLoading() {
		baseActivity?.showLoading()
	}
	
	override fun hideLoading() {
		baseActivity?.hideLoading()
	}
	
	override fun hideKeyboard() {
		baseActivity?.hideKeyboard()
	}
}
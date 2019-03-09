class LoginPresenter: BasePresenter {

    weak var view: LoginPresenterToViewProtocol!
    weak var interactor: LoginPresenterToInteractorProtocol!
    weak var router: LoginPresenterToRotuerProtocol!
    
    var baseView: BasePresenterToViewProtocol {
        get {
            return view
        }
    }
}

extension LoginPresenter: LoginViewToPresenterProtocol {
    
}

extension LoginPresenter: LoginInteractorToPresenterProtocol {
    
}

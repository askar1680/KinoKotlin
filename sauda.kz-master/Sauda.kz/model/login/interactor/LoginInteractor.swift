class LoginInteractor {
    
    weak var presenter: LoginInteractorToPresenterProtocol!
    
}

extension LoginInteractor: LoginPresenterToInteractorProtocol {
    
}

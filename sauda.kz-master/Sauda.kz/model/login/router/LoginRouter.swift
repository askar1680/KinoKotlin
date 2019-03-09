import UIKit

class LoginRouter {
    
    init (navigationController: NavigationController) {
        
        let view = LoginViewController()
        let interactor = LoginInteractor()
        let presenter = LoginPresenter()
        
        view.presenter = presenter
        interactor.presenter = presenter
        presenter.view = view
        presenter.interactor = interactor
        presenter.router = self
        
        navigationController.pushViewController(view, animated: true)
        
    }
    
}

extension LoginRouter: LoginPresenterToRotuerProtocol {
    
}

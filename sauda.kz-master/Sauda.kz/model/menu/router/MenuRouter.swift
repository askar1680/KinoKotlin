class MenuRouter {
    
    init(navigationController: NavigationController) {
        
        let view = MenuTabBarController()
        let interactor = MenuInteractor()
        let presenter = MenuPresenter()
        
        view.presenter = presenter
        interactor.presenter = presenter
        presenter.view = view
        presenter.interactor = interactor
        presenter.router = self
        
        navigationController.pushViewController(view, animated: true)
        
    }
    
}

extension MenuRouter: MenuPresenterToRotuerProtocol {
    
}

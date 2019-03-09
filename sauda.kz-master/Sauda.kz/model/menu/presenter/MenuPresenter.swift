class MenuPresenter: BasePresenter {
    
    weak var view: MenuPresenterToViewProtocol!
    weak var interactor: MenuPresenterToInteractorProtocol!
    weak var router: MenuPresenterToRotuerProtocol!
    
    var baseView: BasePresenterToViewProtocol {
        get {
            return view
        }
    }
    
}

extension MenuPresenter: MenuViewToPresenterProtocol {
    
}

extension MenuPresenter: MenuInteractorToPresenterProtocol {
    
}

class MenuInteractor {
    
    weak var presenter: MenuInteractorToPresenterProtocol!
    
}

extension MenuInteractor: MenuPresenterToInteractorProtocol {
    
}

protocol BasePresenter: class, Loadable, StatusPresentableProtocol {
    var baseView: BasePresenterToViewProtocol { get }
}

extension BasePresenter {
    
    func showError(_ error: AppError, completion: VoidCompletion? = nil) {
        baseView.stopLoading()
        baseView.setUI(interactionEnable: true)
        baseView.showError(message: error.description, completion: completion)
    }
    
    func showError(message: String) {
        baseView.stopLoading()
        baseView.setUI(interactionEnable: true)
        baseView.showError(message: message)
    }
    
    func showSuccess(message: String) {
        baseView.stopLoading()
        baseView.setUI(interactionEnable: true)
        baseView.showSuccess(message: message)
    }
    
    func showOption(message: String, onOptionSelect perform: @escaping (_ isOkOptionSelected: Bool) -> Void) {
        baseView.stopLoading()
        baseView.setUI(interactionEnable: true)
        baseView.showOption(message: message, onOptionSelect: perform)
    }
    
    func startLoading() {
        baseView.startLoading()
        baseView.setUI(interactionEnable: false)
    }
    
    func stopLoading() {
        baseView.startLoading()
        baseView.setUI(interactionEnable: true)
    }
}

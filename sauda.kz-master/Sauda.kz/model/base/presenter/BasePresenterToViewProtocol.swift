import UIKit

protocol BasePresenterToViewProtocol: class, AlertPresentable, Loadable, UISettable {
    func lock()
    func unlock()
}

extension BasePresenterToViewProtocol {

    func showError(message: String, completion: VoidCompletion? = nil) {
        showError(message: message, completion: completion)
    }
    
    func showSuccess(message: String) {
        showSuccess(message: message, completion: nil)
    }
    
    func showOption(message: String, onOptionSelect perform: @escaping (_ isOkOptionSelected: Bool) -> Void) {
        showOption(message: message, onOptionSelect: perform)
    }
    
    func lock() {
        startLoading()
        setUI(interactionEnable: false)
    }
    
    func unlock() {
        stopLoading()
        setUI(interactionEnable: true)
    }
}

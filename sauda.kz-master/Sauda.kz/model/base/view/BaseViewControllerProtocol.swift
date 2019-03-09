import UIKit

protocol BaseViewControllerProtocol: BasePresenterToViewProtocol where Self: UIViewController {
    
    var activityIndicator: UIActivityIndicatorView { get }
    var topmostViewController: UIViewController { get }

}

extension BaseViewControllerProtocol {
    
    var topmostViewController: UIViewController {
        return getTopmostViewController(viewController: self)
    }
    
    func setUI(interactionEnable: Bool) {
        view.isUserInteractionEnabled = interactionEnable
    }
    
    func showError(message: String, completion: VoidCompletion?) {
        let alert = UIAlertController(title: "Внимание", message: message, preferredStyle: .alert)
        let cancel = UIAlertAction(title: "Закрыть", style: .cancel) { _ in
            completion?()
        }
        alert.addAction(cancel)
        present(alert, animated: true, completion: nil)
    }
    
    func showError(_ error: AppError, onDismiss perform: VoidCompletion? = nil) {
        showError(message: error.description, completion: perform)
    }
    
    func showSuccess(message: String, completion: VoidCompletion?) {
        let alert = UIAlertController(title: "Сообщение", message: message, preferredStyle: .alert)
        let cancel = UIAlertAction(title: "Закрыть", style: .cancel) { _ in
            completion?()
        }
        alert.addAction(cancel)
        present(alert, animated: true, completion: nil)
    }
    
    func showOption(message: String, onOptionSelect perform: @escaping (_ isOkOptionSelected: Bool) -> Void) {
        let alertController = UIAlertController(title: "Внимание", message: message, preferredStyle: .alert)
        let okAction = UIAlertAction(title: "OK", style: .default) { _ in perform(true) }
        let cancel = UIAlertAction(title: "Отмена", style: .cancel) { _ in perform(false) }
        alertController.addAction(okAction)
        alertController.addAction(cancel)
        present(alertController, animated: true)
    }
    
    func getTopmostViewController(viewController: UIViewController) -> UIViewController {
        if let tabBarController = tabBarController,
            let selectedViewController = tabBarController.selectedViewController {
            return getTopmostViewController(viewController: selectedViewController)
        } else if let navigationController = navigationController,
            let visibleViewController = navigationController.visibleViewController {
            return getTopmostViewController(viewController: visibleViewController)
        } else if let presentedViewController = viewController.presentedViewController {
            return getTopmostViewController(viewController: presentedViewController)
        }
        return viewController
    }
    
    func startLoading() {
        var indicatorFrameView: UIView = view
        if let tabBarController = tabBarController {
            indicatorFrameView = tabBarController.view
        } else if let navigaitonController = navigationController {
            indicatorFrameView = navigaitonController.view
        }
    
        activityIndicator.frame = CGRect (
            x: 0,
            y: 0,
            width: indicatorFrameView.frame.width,
            height: indicatorFrameView.frame.height
        )
    
        indicatorFrameView.addSubview(activityIndicator)
        activityIndicator.startAnimating()
    }
    
    func stopLoading() {
        activityIndicator.stopAnimating()
        activityIndicator.removeFromSuperview()
    }
}

import UIKit

class NavigationController: UINavigationController, BaseViewControllerProtocol {
    
    var activityIndicator: UIActivityIndicatorView = {
        let activityIndicator = UIActivityIndicatorView()
        activityIndicator.backgroundColor = AppColor.blue.uiColor.withAlphaComponent(0.5)
        return activityIndicator
    }()
    
    override func pushViewController(_ viewController: UIViewController, animated: Bool) {
        let backBarButtonItem = UIBarButtonItem()
        backBarButtonItem.title = ""
        viewControllers.last?.navigationItem.backBarButtonItem = backBarButtonItem
        
        super.pushViewController(viewController, animated: animated)
    }
}

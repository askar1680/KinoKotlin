import UIKit

class BaseTabBarCotnroller: UITabBarController, BaseViewControllerProtocol {
    var activityIndicator: UIActivityIndicatorView = {
        let activtiyIndicator = UIActivityIndicatorView()
        activtiyIndicator.backgroundColor = AppColor.blue.uiColor.withAlphaComponent(0.5)
        return activtiyIndicator
    }()
}

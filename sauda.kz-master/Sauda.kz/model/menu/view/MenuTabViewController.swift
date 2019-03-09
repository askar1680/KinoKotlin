import UIKit

class MenuTabBarController: BaseTabBarCotnroller {
    
    weak var presenter: MenuViewToPresenterProtocol!
    
}

extension MenuTabBarController: MenuPresenterToViewProtocol {
    
}

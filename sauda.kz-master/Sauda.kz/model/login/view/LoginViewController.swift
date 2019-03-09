import UIKit

class LoginViewController: BaseViewController {
    
    weak var presenter: LoginViewToPresenterProtocol!
    
    private var imageView = UIImageView()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        setupViews()
    }
}

extension LoginViewController: ViewInstallationProtocol {
    func addSubviews() {
        view.addSubview(imageView)
    }
    
    func setViewConstraints() {
        var layoutConstraints = [NSLayoutConstraint]()
        
        imageView.translatesAutoresizingMaskIntoConstraints = false
        layoutConstraints += [
            imageView.centerXAnchor.constraint(equalTo: view.centerXAnchor),
            imageView.centerYAnchor.constraint(equalTo: view.centerYAnchor),
            imageView.widthAnchor.constraint(equalToConstant: 100),
            imageView.heightAnchor.constraint(equalToConstant: 100)
        ]
        
        NSLayoutConstraint.activate(layoutConstraints)
    }
    
    func stylizeViews() {
        view.backgroundColor = AppColor.white.uiColor
        title = "Demo"
        
        imageView.image = AppImage.shoppingCart.uiImage
    }
}

extension LoginViewController: LoginPresenterToViewProtocol {
    
}

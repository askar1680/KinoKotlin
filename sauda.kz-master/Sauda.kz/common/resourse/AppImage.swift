import UIKit

enum AppImage: String {
    case appIcon
    case shoppingCart
    
    var uiImage: UIImage? {
        return UIImage(named: rawValue)
    }
    
    var cgImage: CGImage? {
        return self.uiImage?.cgImage
    }
}

import UIKit

enum AppColor {
    case white
    case blue
    case darkBlue
    
    var uiColor: UIColor {
        switch self {
        case .white: return UIColor(rbg: 255, 255, 255)
        case .blue: return UIColor(rbg: 95, 195, 240)
        case .darkBlue: return UIColor(rbg: 55, 115, 235)
        }
    }
    
    var cgColor: CGColor { return uiColor.cgColor }
    
}

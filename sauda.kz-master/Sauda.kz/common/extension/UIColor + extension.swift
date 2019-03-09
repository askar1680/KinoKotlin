import UIKit

extension UIColor {
    
    convenience init(rbg: UInt8..., alpha: CGFloat = 1) {
        self.init(
            red: CGFloat(rbg[0] / 255),
            green: CGFloat(rbg[1] / 255),
            blue: CGFloat(rbg[2] / 255),
            alpha: alpha
        )
    }
}

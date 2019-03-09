import Foundation

protocol AppError: Error {
    var description: String { get }
}

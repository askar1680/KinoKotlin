protocol StatusPresentableProtocol {
    func showError(_ error: AppError, completion: VoidCompletion?)
    func showError(message: String)
    func showSuccess(message: String)
    func showOption(message: String, onOptionSelect perform: @escaping (_ isOkOptionSelected: Bool) -> Void)
}

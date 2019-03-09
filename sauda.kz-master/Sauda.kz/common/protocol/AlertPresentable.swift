protocol AlertPresentable {
    func showError(message: String, completion: VoidCompletion?)
    func showSuccess(message: String, completion: VoidCompletion?)
    func showOption(message: String, onOptionSelect perform: @escaping (_ isOkOptionSelected: Bool) -> Void)
    func showError(_ error: AppError, onDismiss perform: (() -> Void)?)
}

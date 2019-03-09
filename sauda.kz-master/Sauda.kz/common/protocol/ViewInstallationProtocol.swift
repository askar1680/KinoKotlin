protocol ViewInstallationProtocol {
    func setupViews()
    func addSubviews()
    func setViewConstraints()
    func stylizeViews()
    func doSomethingExtra()
}

extension ViewInstallationProtocol {
    func setupViews() {
        addSubviews()
        setViewConstraints()
        stylizeViews()
        doSomethingExtra()
    }
    
    func doSomethingExtra() {}
}

//
//  MaskedTextFieldView.swift
//  TestPodsForATF24
//
//  Created by Аскар on 10/3/19.
//  Copyright © 2019 askar.ulubayev. All rights reserved.
//

import InputMask

class MaskedTextFieldView: UIView {
    
    /// UI elements
    let stackView = UIStackView()
    let titleLabel = UILabel()
    let descriptionTextView = UITextView()
    let placeholderLabel = UILabel()
    let optionalLabel = UILabel()
    
    var maskConfiguration: MaskedTextViewDelegate?
    var extractedValue: String?
    var mandatoryCharactersAreFilled = false
    var justStartedEditing = true
    
    /// Object intended for delegate methods to identify the cell
    var indexPath: IndexPath?
    
    /// Object inteded to perform updates
    weak var delegate: TextFieldViewCellDelegate? {
        didSet { descriptionTextView.delegate = maskConfiguration }
    }
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        
        addSubviews()
        setViewConstraints()
        stylizeViews()
        
        let tapGestureRecognizer = UITapGestureRecognizer(target: self, action: #selector(startEditing))
        addGestureRecognizer(tapGestureRecognizer)
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    private func addSubviews() {
        addSubview(stackView)
        stackView.addArrangedSubview(titleLabel)
        stackView.addArrangedSubview(descriptionTextView)
        addSubview(placeholderLabel)
    }
    
    private func setViewConstraints() {
        // stackView
        stackView.translatesAutoresizingMaskIntoConstraints = false
        stackView.leftAnchor.constraint(equalTo: leftAnchor, constant: 16).isActive = true
        stackView.topAnchor.constraint(equalTo: topAnchor, constant: 10).isActive = true
        stackView.rightAnchor.constraint(equalTo: rightAnchor, constant: -16).isActive = true
        stackView.bottomAnchor.constraint(equalTo: bottomAnchor, constant: -12).isActive = true
        
        // titleLabel
        titleLabel.translatesAutoresizingMaskIntoConstraints = false
        titleLabel.heightAnchor.constraint(greaterThanOrEqualToConstant: 18).isActive = true
        
        // placeholderLabel
        placeholderLabel.translatesAutoresizingMaskIntoConstraints = false
        placeholderLabel.topAnchor.constraint(equalTo: descriptionTextView.topAnchor, constant: 4).isActive = true
        placeholderLabel.leftAnchor.constraint(equalTo: descriptionTextView.leftAnchor).isActive = true
        placeholderLabel.rightAnchor.constraint(equalTo: descriptionTextView.rightAnchor).isActive = true
    }
    
    private func stylizeViews() {
        // stackView
        stackView.axis = .vertical
        stackView.distribution = .fill
        
        // titleLabel
        titleLabel.font = UIFont.systemFont(ofSize: 14)
        titleLabel.textColor = .gray
        titleLabel.isUserInteractionEnabled = false
        titleLabel.numberOfLines = 0
        
        // descriptionTextField
        descriptionTextView.isUserInteractionEnabled = false
        descriptionTextView.isScrollEnabled = false
        descriptionTextView.font = .systemFont(ofSize: 16)
        descriptionTextView.textContainer.lineBreakMode = .byTruncatingHead
        descriptionTextView.textColor = .black
        descriptionTextView.keyboardType = .default
        descriptionTextView.backgroundColor = .clear
        descriptionTextView.autocorrectionType = .no
        descriptionTextView.returnKeyType = .default
        descriptionTextView.autocapitalizationType = .none
        descriptionTextView.textContainerInset = UIEdgeInsets(top: 4, left: -5, bottom: 0, right: 0)
        
        // placeholderLabel
        placeholderLabel.backgroundColor = .clear
        placeholderLabel.isUserInteractionEnabled = false
        placeholderLabel.numberOfLines = 0
        placeholderLabel.font = .systemFont(ofSize: 16)
        placeholderLabel.textColor = UIColor.lightGray
        
        // optionalLabel
        optionalLabel.isUserInteractionEnabled = false
        optionalLabel.numberOfLines = 0
        optionalLabel.font = .systemFont(ofSize: 14)
    }
    
    /// Method to be called before cell reuse
    func prepareForReuse() {
        delegate = nil
        indexPath = nil
        titleLabel.text = nil
        titleLabel.font = .systemFont(ofSize: 14)
        descriptionTextView.isUserInteractionEnabled = false
        descriptionTextView.text = nil
        descriptionTextView.keyboardType = .default
        descriptionTextView.autocapitalizationType = .none
        descriptionTextView.inputView = nil
        descriptionTextView.font = .systemFont(ofSize: 16)
        placeholderLabel.text = nil
        placeholderLabel.font = .systemFont(ofSize: 16)
        isUserInteractionEnabled = true
        optionalLabel.text = nil
        optionalLabel.font = .systemFont(ofSize: 14)
        maskConfiguration = nil
        justStartedEditing = true
        mandatoryCharactersAreFilled = false
    }
    
    @objc func startEditing() {
        if let delegate = delegate,
           let indexPath = indexPath {
            
            // Put cursor to the end of the field
            descriptionTextView.selectedTextRange = descriptionTextView.textRange(
                from: descriptionTextView.endOfDocument,
                to: descriptionTextView.endOfDocument
            )
            
            delegate.shouldBeginEditing(description: extractedValue ?? "", forCellAt: indexPath)
        }
        descriptionTextView.becomeFirstResponder()
        descriptionTextView.isUserInteractionEnabled = true
    }
    
    @objc private func finishEditing() {
        descriptionTextView.isUserInteractionEnabled = false
        endEditing(true)
        
        if let delegate = delegate,
           let indexPath = indexPath,
           let extractedValue = extractedValue {
            delegate.didEndEditing(description: extractedValue, forCellAt: indexPath)
        }
    }
    
    @objc private func performOnOptionalActionButtonPress() {
        if let delegate = delegate,
           let indexPath = indexPath {
            descriptionTextView.resignFirstResponder()
            delegate.didPressOptionalActionKeyboardButton(description: descriptionTextView.text, forCellAt: indexPath)
        }
    }

}

extension MaskedTextFieldView: MaskedTextViewDelegateListener {
    
    func textView(
        _ textView: UITextView,
        didFillMandatoryCharacters complete: Bool,
        didExtractValue value: String
    ) {
        mandatoryCharactersAreFilled = complete
        placeholderLabel.isHidden = !descriptionTextView.text.isEmpty
        extractedValue = value
        
        if let delegate = delegate,
           let indexPath = indexPath {
            delegate.update(description: value, forCellAt: indexPath)
        }
    }
}

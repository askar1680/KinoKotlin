//
//  MaskedTextFieldCell.swift
//  TestPodsForATF24
//
//  Created by Аскар on 10/3/19.
//  Copyright © 2019 askar.ulubayev. All rights reserved.
//

import InputMask

/// Masked table view cell class that contains title with mandatory and optional descriptions;
class MaskedTextFieldCell: UITableViewCell {
    
    private let view = MaskedTextFieldView()
    
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        contentView.addSubview(view)
        setViewConstraints()
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    private func setViewConstraints() {
        view.translatesAutoresizingMaskIntoConstraints = false
        view.leftAnchor.constraint(equalTo: contentView.leftAnchor).isActive = true
        view.topAnchor.constraint(equalTo: contentView.topAnchor).isActive = true
        view.rightAnchor.constraint(equalTo: contentView.rightAnchor).isActive = true
        view.bottomAnchor.constraint(equalTo: contentView.bottomAnchor).isActive = true
    }
    
    override func prepareForReuse() {
        super.prepareForReuse()
        view.prepareForReuse()
    }
}

extension MaskedTextFieldCell {
    
    var delegate: TextFieldViewCellDelegate? {
        get { return view.delegate }
        set { view.delegate = newValue }
    }
    
    var indexPath: IndexPath? {
        get { return view.indexPath }
        set { view.indexPath = newValue }
    }

    var title: String? {
        get { return view.titleLabel.text }
        set { view.titleLabel.text = newValue }
    }
    
    @available(*, unavailable, message:"Variable is unavailable")
    override var description: String { return super.description }
    
    var mandatoryCharactersAreFilled: Bool {
        return view.mandatoryCharactersAreFilled
    }
    
    func set(description: String?) {
        view.placeholderLabel.isHidden = description?.isEmpty == false
        
        guard view.maskConfiguration != nil,
              let text = description,
              let maskConfiguration = view.maskConfiguration else {
            return
        }
        
        maskConfiguration.put(text: text, into: view.descriptionTextView)
    }
    
    var isEditable: Bool {
        get { return view.isUserInteractionEnabled }
        set { view.isUserInteractionEnabled = newValue }
    }
    
    var placeholder: String? {
        get { return view.placeholderLabel.text }
        set { view.placeholderLabel.text = newValue }
    }
    
    func setOptional(description: String?, asWarning: Bool = false) {
        view.optionalLabel.text = description
        view.optionalLabel.textColor = asWarning ? .red : .brown
        
        if let description = description, !description.isEmpty {
            if !view.stackView.arrangedSubviews.contains(view.optionalLabel) {
                view.stackView.addArrangedSubview(view.optionalLabel)
            }
        } else {
            view.stackView.removeArrangedSubview(view.optionalLabel)
        }
    }
    
    func resize(by factor: CGFloat) {
        
        let initialTitleLabelFontSize = view.titleLabel.font.pointSize
        view.titleLabel.font = view.titleLabel.font.withSize(factor * initialTitleLabelFontSize)
        
        if let initialDescriptionTextViewFontSize = view.descriptionTextView.font?.pointSize {
            view.descriptionTextView.font = view.descriptionTextView.font?.withSize(
                factor * initialDescriptionTextViewFontSize
            )
        }
        
        let initialPlaceholderLabelFontSize = view.placeholderLabel.font.pointSize
        view.placeholderLabel.font = view.placeholderLabel.font.withSize(factor * initialPlaceholderLabelFontSize)
        
        let initialOptionalLabelFontSize = view.optionalLabel.font.pointSize
        view.optionalLabel.font = view.optionalLabel.font.withSize(factor * initialOptionalLabelFontSize)
    }
    
    func set(maskFormat: String, keyboardType: UIKeyboardType) {
        let maskedTextViewDelegate = MaskedTextViewDelegate()
        maskedTextViewDelegate.primaryMaskFormat = maskFormat
        maskedTextViewDelegate.listener = view
        maskedTextViewDelegate.affinityCalculationStrategy = .prefix
        
        view.maskConfiguration = maskedTextViewDelegate
        view.descriptionTextView.keyboardType = keyboardType
    }
    
    func set(autocapitalizationType: UITextAutocapitalizationType) {
        view.descriptionTextView.autocapitalizationType = autocapitalizationType
    }
    
    func startEditing() {
        view.startEditing()
    }
}

//
//  TextFieldViewCellDelegate.swift
//  TestPodsForATF24
//
//  Created by Аскар on 10/3/19.
//  Copyright © 2019 askar.ulubayev. All rights reserved.
//

import Foundation

protocol TextFieldViewCellDelegate: class {
    func update(description: String, forCellAt indexPath: IndexPath)
    func shouldBeginEditing(description: String, forCellAt indexPath: IndexPath)
    func didBeginEditing(description: String, forCellAt indexPath: IndexPath)
    func didEndEditing(description: String, forCellAt indexPath: IndexPath)
    func didPressOptionalActionKeyboardButton(description: String, forCellAt indexPath: IndexPath)
    func update(optionalDescription: String, forCellAt indexPath: IndexPath)
}

extension TextFieldViewCellDelegate {
    func update(description: String, forCellAt indexPath: IndexPath) {}
    func shouldBeginEditing(description: String, forCellAt indexPath: IndexPath) {}
    func didBeginEditing(description: String, forCellAt indexPath: IndexPath) {}
    func didEndEditing(description: String, forCellAt indexPath: IndexPath) {}
    func didPressOptionalActionKeyboardButton(description: String, forCellAt indexPath: IndexPath) {}
    func update(optionalDescription: String, forCellAt indexPath: IndexPath) {}
}

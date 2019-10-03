//
//  InputMaskViewController.swift
//  TestPodsForATF24
//
//  Created by Аскар on 10/3/19.
//  Copyright © 2019 askar.ulubayev. All rights reserved.
//

import UIKit

class InputMaskViewController: UITableViewController {
    
    override func viewDidLoad() {
        super.viewDidLoad()
        tableView.register(MaskedTextFieldCell.self, forCellReuseIdentifier: "MaskedTextFieldCell")
    }
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 5
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(
            withIdentifier: "MaskedTextFieldCell",
            for: indexPath
        ) as! MaskedTextFieldCell
        
        cell.isEditable = true
        cell.title = "Title"
        cell.set(maskFormat: "+7 [000] [000] [00] [00][--------]", keyboardType: .emailAddress)
        cell.set(description: "3244434243")
        cell.delegate = self
        cell.indexPath = indexPath
        return cell
    }
}

extension InputMaskViewController: TextFieldViewCellDelegate {
    func update(description: String, forCellAt indexPath: IndexPath) {
        print(description)
    }
}

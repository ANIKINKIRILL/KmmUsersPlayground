//
//  UsersViewModel.swift
//  iosApp
//
//  Created by Kirill Anikin on 13.10.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

extension UserListScreen {
    @MainActor class UsersViewModel: ObservableObject {
        private var getUsersUseCase: GetUsersUseCase? = nil
        
        @Published private(set) var users = [User]()
        
        init(getUsersUseCase: GetUsersUseCase? = nil) {
            self.getUsersUseCase = getUsersUseCase
        }
        
        func loadUsers() {
            getUsersUseCase?.execute(completionHandler: { users, error in
                self.users = users ?? []
            })
        }
        
        func setGetUsersUseCase(getUserUseCase: GetUsersUseCase) {
            self.getUsersUseCase = getUserUseCase
        }
    }
}

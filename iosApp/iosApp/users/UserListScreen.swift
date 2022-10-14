//
//  UserListScreen.swift
//  iosApp
//
//  Created by Kirill Anikin on 13.10.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct UserListScreen: View {
    private var getUsersUseCase: GetUsersUseCase
    @StateObject var viewModel = UsersViewModel(getUsersUseCase: nil)
    
    init(getUsersUseCase: GetUsersUseCase) {
        self.getUsersUseCase = getUsersUseCase
    }
    
    var body: some View {
        VStack {
            List {
                ForEach(viewModel.users, id: \.self.id) { user in
                    UserItem(user: user)
                }
            }
            .onAppear {
                viewModel.loadUsers()
            }
            .listStyle(.plain)
        }
        .onAppear {
            viewModel.setGetUsersUseCase(getUserUseCase: getUsersUseCase)
        }
    }
}

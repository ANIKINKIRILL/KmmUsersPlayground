//
//  UserRowItem.swift
//  iosApp
//
//  Created by Kirill Anikin on 13.10.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

struct UserItem: View {
    var user: User
    
    var body: some View {
        VStack(alignment: .leading) {
            HStack {
                Text(user.name)
                    .font(.title3)
                    .fontWeight(.semibold)
                Spacer()
            }.padding(.bottom, 3)
            
            Text(user.username)
                .fontWeight(.light)
                .padding(.bottom, 3)
        }
        .padding()
        .clipShape(RoundedRectangle(cornerRadius: 5.0))
    }
}

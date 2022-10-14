import SwiftUI
import shared

@main
struct iOSApp: App {
    
    var _iosHttpClient: Ktor_client_coreHttpClient
    var _userServiceImpl: UserServiceImpl
    var _userRepositoryImpl: UserRepositoryImpl
    var _getUsersUseCase: GetUsersUseCase
    
    init() {
        self._iosHttpClient = IosHttpClientKt.iosHttpClient()
        self._userServiceImpl = UserServiceImpl(httpClient: _iosHttpClient)
        self._userRepositoryImpl = UserRepositoryImpl(remote: _userServiceImpl, mapper: UserMapper())
        self._getUsersUseCase = GetUsersUseCase(userRepository: _userRepositoryImpl)
    }

    
	var body: some Scene {
		WindowGroup {
			UserListScreen(getUsersUseCase: _getUsersUseCase)
		}
	}
}

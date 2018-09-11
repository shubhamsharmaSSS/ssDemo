class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller: 'public', action: 'login')
        "/login/auth?login_error=1"(controller: 'public' , action: 'login')
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}

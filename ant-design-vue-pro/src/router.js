import Vue from "vue";
import Router from "vue-router";
import Home from "./views/Home.vue";

Vue.use(Router);

export default new Router({
  mode: "history",
  base: process.env.BASE_URL,
  routes: [
    {
      path: "/user",
      component: () =>
        import(/* webpackChunkName: "layout" */ "./layouts/UserLayout"),
      children: [
        {
          path: "/user",
          redirect: "/user/login"
        },
        {
          path: "/user/login",
          name: "login",
          component: () =>
            import(/* webpackChunkName: "user" */ "./views/user/Login")
        },
        {
          path: "/user/register",
          name: "register",
          component: () =>
            import(/* webpackChunkName: "user" */ "./views/user/Register")
        }
      ]
    },
    {
      path: "/",
      component: () =>
        import(/* webpackChunkName: "layout" */ "./layouts/BasicLayout"),
      children: [
        {
          path: "/",
          redirect: "/dashboard/analysis"
        },
        {
          path: "/dashboard",
          name: "dashboard",
          component: { render: h => h("router-view") },
          children: [
            {
              path: "/dashboard/analysis",
              name: "analysis",
              component: () =>
                import(
                  /* webpackChunkName: "dashboard" */ "./views/dashboard/Analysis"
                )
            }
          ]
        },
        {
          path: "/form",
          name: "form",
          component: { render: h => h("router-view") },
          children: [
            {
              path: "/form/basic-form",
              name: "basicform",
              component: () =>
                import(/* webpackChunkName: "form" */ "./views/forms/BaseForm")
            },
            {
              path: "/form/step-form",
              name: "stepform",
              component: () =>
                import(/* webpackChunkName: "form" */ "./views/forms/stepform"),
              children: [
                {
                  path: "/form/step-form",
                  redirect: "/form/step-form/info"
                },
                {
                  path: "/form/step-form/info",
                  name: "info",
                  component: () =>
                    import(
                      /* webpackChunkName: "form" */ "./views/forms/stepform/Step1"
                    )
                },
                {
                  path: "/form/step-form/confirm",
                  name: "confirm",
                  component: () =>
                    import(
                      /* webpackChunkName: "form" */ "./views/forms/stepform/Step2"
                    )
                },
                {
                  path: "/form/step-form/result",
                  name: "result",
                  component: () =>
                    import(
                      /* webpackChunkName: "form" */ "./views/forms/stepform/Step3"
                    )
                }
              ]
            }
          ]
        }
      ]
    },
    {
      path: "/",
      name: "home",
      component: Home
    },
    {
      path: "/about",
      name: "about",
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () =>
        import(/* webpackChunkName: "about" */ "./views/About.vue")
    }
  ]
});

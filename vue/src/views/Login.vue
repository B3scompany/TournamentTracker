<template>
  <div class="text-center" id="login">
    <div class="login-page">
            <div class="login-header">
              <h3>Welcome To Tournament Tracker</h3>
            </div>
      <form class="form form-signin form" @submit.prevent="login">
        <h1 class="h3 mb-3 font-weight-normal">Please Sign In</h1>
        <div
          class="alert alert-danger"
          role="alert"
          v-if="invalidCredentials"
        >Invalid username and password!</div>
        <div
          class="alert alert-success"
          role="alert"
          v-if="this.$route.query.registration"
        >Thank you for registering, please sign in.</div>
        <label id="user" for="username" class="sr-only">Please enter your username below</label>
        <input
          type="text"
          id="username"
          class="form-control"
          placeholder="Username"
          v-model="user.username"
          required
          autofocus
        />
        <label id="pass" for="password" class="sr-only">Please enter your password below</label>
        <input
          type="password"
          id="password"
          class="form-control"
          placeholder="Password"
          v-model="user.password"
          required
        />
          <router-link id="register" :to="{ name: 'register' }">Need an account?</router-link>
          <button id="submitButton" type="submit">Sign in</button>
      </form>
    </div>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style scoped>
/* header .header{
  background-color: #fff;
  height: 45px;
}
header a img{
  width: 134px;
margin-top: 4px;
} */
.login-page {
  width: 360px;
  padding: 8% 0 0;
  margin: auto;
  font-family: "Roboto", sans-serif;
}
.login-page .form .login{
  margin-top: -31px;
margin-bottom: 26px;
}
.form {
  position: relative;
  z-index: 1;
  background:black#FFFFFF;
  max-width: 360px;
  margin: 0 auto 100px;
  padding: 45px;
  text-align: center;
  box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
}
.form input {
  font-family: "Roboto", sans-serif;
  outline: 0;
  background: #f2f2f2;
  width: 100%;
  border: 0;
  margin: 0 0 15px;
  padding: 15px;
  box-sizing: border-box;
  font-size: 14px;
}
.form button {
  font-family: "Roboto", sans-serif;
  text-transform: uppercase;
  outline: 0;
  background-color: goldenrod;
  /* background-image: linear-gradient(45deg,#328f8a,#08ac4b); */
  width: 100%;
  border: 0;
  padding: 15px;
  color: #FFFFFF;
  font-size: 14px;
  -webkit-transition: all 0.3 ease;
  transition: all 0.3 ease;
  cursor: pointer;
}
.form .message {
  margin: 15px 0 0;
  color:blue;
  font-size: 12px;
}
.form .message a {
  color:black;
  text-decoration: none;
}

.container {
  position: relative;
  z-index: 1;
  max-width: 300px;
  margin: 0 auto;
}

/* router-link {
  color: blue;
} */

body {
  background-color: goldenrod;
  background-image: linear-gradient(45deg,goldenrod,goldenrod);
  font-family: "Roboto", sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

p {
  font-family: "Roboto", sans-serif;
  font-size: x-large;
  color: goldenrod;
  margin: auto;
  padding-left: 18%;
  
}

#register{
  font-family: "Roboto", sans-serif;
}

#submitButton{
  margin-top: 3.5%;
}

#password{
  margin-top: 3.5%;
}

#username{
  margin-top: 3.5%;
}

/* #username {
  display: flex;
  flex-direction: column;
  justify-items: center;
} */


/* 
.sr-only{
  margin: 0% auto;
  padding-bottom: 5%;
} */


</style>


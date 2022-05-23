import Vue from 'vue'
import { extend, ValidationObserver, ValidationProvider } from "vee-validate";
import { max, min, required,email, confirmed} from "vee-validate/dist/rules";

extend("max", {
  ...max,
  message: `Password must be less than {length} characters`,
});
extend("min", {
  ...min,
  message: `Password must be more than {length} characters`,
});
extend("required", {
  ...required,
  message: `requrired`,
});
extend("email", {
  ...email,
  message: `Must be a valid email`,
});
extend('confirmed', {
  ...confirmed,
  message: 'Confirm your password'
})

Vue.component('ValidationObserver',ValidationObserver)
Vue.component('ValidationProvider',ValidationProvider)
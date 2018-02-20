import {LOGIN_SUCCESS, LOGOUT} from "../constants/actionsType";

const initialState = {
    login: false
};

export default function login(state = initialState, action){
    console.log("login reducer");
    switch (action.type){
        case LOGIN_SUCCESS:
            return {...state, login: true};
        case LOGOUT:
            return {...state, login: false};
        default:
            return state
    }
}
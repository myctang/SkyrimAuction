import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import $ from "jquery";
import {Redirect} from 'react-router-dom';
import Input from 'react-toolbox/lib/input';
import Button from 'react-toolbox/lib/Button';
import Dialog from 'react-toolbox/lib/dialog';
import { Table, TableHead, TableRow, TableCell } from 'react-toolbox/lib/table';

export class Admin extends Component {

    state = {
        forbidden: false,
        users: [],
        items: [],
        usersOpened: false,
        itemsOpened: false,
        itemId: "",
        userId: "",
    };

    constructor(props){
        super(props);
        if ($.cookie("access_token")){
            let token = $.cookie("access_token");
            console.log("Admin->constructor");
            debugger;
            $.ajax({
                url: "/admin/check",
                method: "GET",
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("Authorization", "Bearer " + token);
                },
            });
        }else{
            this.setState({forbidden: true})
        }
    };

    getUsers = () => {
        console.log("Admin->getUsers");
        let token = $.cookie("access_token")
        $.ajax({
            method: "GET",
            url: "/admin/users",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Authorization", "Bearer " + token);
            },
        }).then((data) =>{
            this.setState({...this.state, users:data})
        })
        this.reverseUsersDialog();
    };

    getItems = () => {
        let token = $.cookie("access_token")
        $.ajax({
            method: "GET",
            url: "/admin/items",
            headers: {"Authorization": "Bearer " + token},
        }).then((data) => {
            this.setState({...this.state, items: data})
        });
        this.reverseItemsDialog()
    };

    reverseUsersDialog = () => {
        this.setState({...this.state, usersOpened: !this.state.usersOpened})
    };

    reverseItemsDialog = () => {
        this.setState({...this.state, itemsOpened: !this.state.itemsOpened})
    };

    addItem = () => {
        let token = $.cookie("access_token")
        let data = {
            "userId":parseInt(this.state.userId),
            "itemId":parseInt(this.state.itemId)
        }
        $.ajax({
            method: "POST",
            url: "/admin/setItem",
            headers: {"Authorization": "Bearer " + token},
            data: JSON.stringify(data),
        }).then((data) => {
            
        });
    };

    handleChange = (name, value) => {
        this.setState({...this.state, [name]: value});
    };

    render() {
        if (this.state.forbidden){
            return <Redirect to="/"/>
        }
        console.log("Admin->render");
        debugger;
        return (
            <div className="content">
                <Button label="Пользователи" raised style={{marginRight: '10px'}} onClick={this.getUsers}/>
                <Dialog
                    active={this.state.usersOpened}
                    onEscKeyDown={this.reverseUsersDialog}
                    onOverlayClick={this.reverseUsersDialog}
                    title='Пользователи'
                >
                    <Table selectable={false}>
                        <TableHead>
                            <TableCell>Id</TableCell>
                            <TableCell>Username</TableCell>
                            <TableCell>First name</TableCell>
                            <TableCell>Second name</TableCell>
                            <TableCell>Money</TableCell>
                            <TableCell>Telegram Id</TableCell>
                        </TableHead>
                        {this.state.users.map((item, idx) => (
                            <TableRow key={idx}>
                                <TableCell>{item.id}</TableCell>
                                <TableCell>{item.name}</TableCell>
                                <TableCell>{item.firstName}</TableCell>
                                <TableCell>{item.secondName}</TableCell>
                                <TableCell>{item.money}</TableCell>
                                <TableCell>{item.telegramID}</TableCell>
                            </TableRow>
                        ))}
                    </Table>
                </Dialog>

                <Button label="Предметы" raised style={{marginRight: '10px'}} onClick={this.getItems}/>
                <Dialog
                    active={this.state.itemsOpened}
                    onEscKeyDown={this.reverseItemsDialog}
                    onOverlayClick={this.reverseItemsDialog}
                    title='Предметы'
                >
                    <Table selectable={false}>
                        <TableHead>
                            <TableCell>Id</TableCell>
                            <TableCell>Name</TableCell>
                            <TableCell>Damage</TableCell>
                            <TableCell>Defence</TableCell>
                            <TableCell>Weight</TableCell>
                        </TableHead>
                        {this.state.items.map((item, idx) => (
                            <TableRow key={idx}>
                                <TableCell>{item.id}</TableCell>
                                <TableCell>{item.name}</TableCell>
                                <TableCell>{item.damage}</TableCell>
                                <TableCell>{item.defence}</TableCell>
                                <TableCell>{item.weight}</TableCell>
                            </TableRow>
                        ))}
                    </Table>
                </Dialog>
                <br/>
                <Input type="text" label="Id предмета" name="itemId" value={this.state.itemId} onChange={this.handleChange.bind(this, 'itemId')} />
                <Input type="text" label="Id пользователя" name="userId" value={this.state.userId} onChange={this.handleChange.bind(this, 'userId')} />
                <Button label="Добавить предметы" raised onClick={this.addItem}/>
            </div>
        )
    }
}

const mapStateToProps = (state) => ({
  
})

const mapDispatchToProps = {
  
}

export default connect(mapStateToProps, mapDispatchToProps)(Admin)

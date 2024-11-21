<script>
export default {
    data() {
        return {
            formData: {
                username: "",
                password: ""
            }
        }
    },
    methods: {
        async submit() {
            const options = {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(this.formData)
            };
            const response = await fetch('http://localhost:8080/accounts', options);
            if (response.ok) {
                alert('Compte créé avec succès');
            } else {
                console.error('Dev is a failure!');
            }
        },

    }
}
</script>
<template>
    <h1>Créer un compte</h1>
    <form @submit.prevent="submit" novalidate>
        <div>
            <label for="username">Nom d'utilisateur</label>
            <input type="email" name="username" id="username" v-model="formData.username">
        </div>
        <div>
            <label for="password">Mot de passe</label>
            <input type="password" name="password" id="password" v-model="formData.password">
        </div>
        <button type="submit">Créer le compte</button>
    </form>
</template>
<style scoped>
h1 {
    color: blue;
}

div {
    margin-top: 1rem;
    display: flex;
    flex-direction: column;
    width: fit-content;
}

button {
    margin-top: 1rem;
}

label::after {
    content: ' *';
    color: red;
}
</style>
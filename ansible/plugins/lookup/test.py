from ansible.plugins.lookup import LookupBase


class LookupModule(LookupBase):
    def run(self, terms, variables=None, **kwargs):
        result = []
        if kwargs['value'] in variables:
            result.append(variables[kwargs['value']])
        else:
            result.append("Value not found in runtime")
        
        return result
